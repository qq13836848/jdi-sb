package jdi.springboot.springmvc.constants;

import jdi.springboot.springmvc.validators.IntArrayValuable;

import java.util.Arrays;

public enum GenderEnum implements IntArrayValuable {
  MALE(1, "male"),
  FEMALE(2, "female");

  public static final int[] ARRAYS =
      Arrays.stream(values()).mapToInt(GenderEnum::getValue).toArray();

  private final Integer value;

  private final String name;

  GenderEnum(Integer value, String name) {
    this.value = value;
    this.name = name;
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }

  public Integer getValue() {
    return value;
  }

  public String getName() {
    return name;
  }
}
