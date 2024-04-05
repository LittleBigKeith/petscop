# Virtual Pet Project
##### Author: Keith Kong

---
A virtual pet raising platform that hosts a retreat for anyone after a busy day.

## Terminology
---
**Owned pets** -  Unique pets that users own
**Shared pets** - Pet blueprints on the system level
**Owned food** - Unique food items that users own
**Shared pets** - Food blueprints on the system level

## Functionalities
---
- One owner should be able to have zero or many owned pets
- One owned pet should have one owner
- Many owned pets can be cloned from one shared pet
- Duplicated (owner, shared pet) pairs are allowed, and should have unique owned pet ids

<br>

- Owner should be able to own zero or many owned food
- One owned food should have one owner
- Many owned food can be cloned from one shared food
- Duplicated (owner, shared food) pairs are NOT allowed. (Owner_id, food_id) should be unique composite ids.

## TODO
---
[ ] missing a pet shop page in mockup, but it's essentially the same as food shop
[ ] missing login stuff
