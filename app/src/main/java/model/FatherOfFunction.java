package model;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Abstract class to avoid duplicated method.
 */
public abstract class FatherOfFunction {
  protected LocalDate creationDate;
  protected String id;

  /**
   * Gets the unique identifier.
   *
   * @return The ID.
   *
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the ID. If the provided ID is null, an ID is auto-generated
   * using the current time in nanoseconds.
   *
   * @param id The ID to set.
   *
   */
  protected void setId() {
    if (this.id == null) {
      this.id = generateUniqueId();
    }
  }

  /**
   * Set currentdate as creationd date.
   */
  protected LocalDate setCreationDate() {
    if (this.creationDate == null) {
      this.creationDate = LocalDate.now();
      return creationDate;
    }
    return creationDate;
  }

  /**
   * Gets the member's creation date.
   *
   * @return The creation date as an integer representing the day.
   *
   */
  public LocalDate getCreationDate() {
    return creationDate;
  }

  /**
   * Generate Unique Id.
   *
   * @return unique Id.
   *
   */
  protected String generateUniqueId() {
    String uuid = UUID.randomUUID().toString();
    return uuid.substring(0, 6); // Take the first 5 characters
  }

  /**
   * Avoid finalizer attack.
   */
  protected final void finalize() throws Throwable {
    // Tom finalizer för att förhindra attacker
  }

}
