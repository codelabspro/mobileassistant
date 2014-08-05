package com.codesdk.mobileassistant;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Offer entity.
 */
@Entity
public class Offer {
  @Id
  private String offerId;

  private String title;

  private String description;

  private String imageUrl;

  public String getOfferId() {
    return offerId;
  }

  public void setOfferID(String offerID) {
    this.offerId = offerID;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

}