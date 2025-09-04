package com.gabrielalbernazdev.backend_cinefy.catalog.movie;

import com.gabrielalbernazdev.backend_cinefy.common.exception.DomainException;

public class InvalidMovieTransitionException extends DomainException {
  public InvalidMovieTransitionException(ExhibitionStatus current, String action) {
    super("Invalid transition: state=" + current + " cannot perform action=" + action);
  }
}
