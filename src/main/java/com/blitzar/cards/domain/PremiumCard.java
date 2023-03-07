package com.blitzar.cards.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "premium_card")
@PrimaryKeyJoinColumn(name="card_id")
public class PremiumCard extends Card {
}
