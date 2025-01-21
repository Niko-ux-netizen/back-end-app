package be.ucll.mobileapplications.team7.Movie.model;

public enum Genre {
  ACTION,
  ADVENTURE,
  ANIMATION,
  BIOGRAPHY,
  COMEDY,
  CRIME,
  DOCUMENTARY,
  DRAMA,
  FAMILY,
  FANTASY,
  HISTORY,
  HORROR,
  MUSICAL,
  MYSTERY,
  ROMANCE,
  SCIENCE_FICTION,
  SPORT,
  THRILLER,
  WAR,
  WESTERN;
}

@JsonValue
public String getGenreName() {
  return this.name();
}

@JsonCreator
public static Genre fromString(String value) {
  return Genre.valueOf(value.toUpperCase());
}