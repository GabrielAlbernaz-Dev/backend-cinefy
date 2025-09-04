package com.gabrielalbernazdev.backend_cinefy.catalog.movie;

public enum ExhibitionStatus {
    DRAFT_UNRELEASED {
        @Override
        public ExhibitionStatus releasePreview() {
            return PREVIEW_COMING_SOON;
        }
    },
    PREVIEW_COMING_SOON {
        @Override
        public ExhibitionStatus startShowing() {
            return NOW_SHOWING;
        }

        @Override
        public ExhibitionStatus putOnHold() {
            return ON_HOLD;
        }
    },
    NOW_SHOWING {
        @Override
        public ExhibitionStatus endExhibition() {
            return ENDED;
        }

        @Override
        public ExhibitionStatus putOnHold() {
            return ON_HOLD;
        }
    },
    ENDED {
        @Override
        public ExhibitionStatus archive() {
            return ARCHIVED;
        }
    },
    ON_HOLD {
        @Override
        public ExhibitionStatus resumePreview() {
            return PREVIEW_COMING_SOON;
        }

        @Override
        public ExhibitionStatus resumeShowing() {
            return NOW_SHOWING;
        }
    },
    ARCHIVED;

    public ExhibitionStatus releasePreview() {
        throw invalid("releasePreview");
    }

    public ExhibitionStatus startShowing() {
        throw invalid("startShowing");
    }

    public ExhibitionStatus endExhibition() {
        throw invalid("endExhibition");
    }

    public ExhibitionStatus putOnHold() {
        throw invalid("putOnHold");
    }

    public ExhibitionStatus resumePreview() {
        throw invalid("resumePreview");
    }

    public ExhibitionStatus resumeShowing() {
        throw invalid("resumeShowing");
    }

    public ExhibitionStatus archive() {
        throw invalid("archive");
    }

    private InvalidMovieTransitionException invalid(String action) {
        return new InvalidMovieTransitionException(this, action);
    }
}

