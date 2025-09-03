CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE TABLE IF NOT EXISTS genres (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(64) UNIQUE NOT NULL,
    name VARCHAR(155) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ
);

CREATE INDEX IF NOT EXISTS ix_genres_active ON genres(active);
CREATE UNIQUE INDEX IF NOT EXISTS ux_genres_code ON genres(code);

CREATE TABLE IF NOT EXISTS persons (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(155) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ
);

CREATE INDEX IF NOT EXISTS ix_persons_name ON persons(name);

CREATE TABLE IF NOT EXISTS movies (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(155) NOT NULL,
    description VARCHAR(2000),
    status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',
    release_year INT,
    duration_min INT,
    indicative_rating VARCHAR(3) DEFAULT 'L',
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ
);

CREATE INDEX IF NOT EXISTS ix_movies_status ON movies(status);
CREATE INDEX IF NOT EXISTS ix_movies_release_year ON movies(release_year);
CREATE INDEX IF NOT EXISTS ix_movies_title_trgm ON movies USING GIN (title gin_trgm_ops);

CREATE TABLE IF NOT EXISTS movies_genres (
    movie_id UUID NOT NULL REFERENCES movies(id) ON DELETE CASCADE,
    genre_id UUID NOT NULL REFERENCES genres(id) ON DELETE RESTRICT,
    PRIMARY KEY (movie_id, genre_id)
);

CREATE INDEX IF NOT EXISTS ix_movies_genres_genre ON movies_genres(genre_id);

CREATE TABLE IF NOT EXISTS movie_cast (
    movie_id UUID NOT NULL REFERENCES movies(id) ON DELETE CASCADE,
    person_id UUID NOT NULL REFERENCES persons(id) ON DELETE RESTRICT,
    role VARCHAR(10) NOT NULL DEFAULT 'OTHER',
    character_name VARCHAR(155),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (movie_id, person_id, role)
);

CREATE INDEX IF NOT EXISTS ix_movie_cast_movie ON movie_cast(movie_id);
CREATE INDEX IF NOT EXISTS ix_movie_cast_person ON movie_cast(person_id);
CREATE INDEX IF NOT EXISTS ix_movie_cast_role ON movie_cast(role);
CREATE INDEX IF NOT EXISTS ix_movie_cast_billing ON movie_cast(movie_id, role);
