package com.traptricker.objects;

/**
 * This enum holds the IDs for all the game objects, e.g. Player, Enemy, Coin etc.
 */
public enum ID {

  // Player
  Player(),

  // UI
  TitleScreenEnemy(),

  // Enemies
  BasicEnemy(),
  StreakEnemy(),
  HealingEnemy(),
  HomingEnemy(),
  InstantDeathEnemy(),
  FireworkEnemy(),
  ShooterEnemy(),

  // Bosses
  BouncyBoss(),

  // Projectiles
  ShooterProjectile(),

  // Powerups
  ShrinkPowerup(),
  ShieldPowerup()

}
