package com.blitzar.cards.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "standard_card")
@PrimaryKeyJoinColumn(name="card_id")
public class StandardCard extends Card {
}
