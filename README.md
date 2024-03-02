# badatz-word-similarity-backend

## How To Use
Clone Repo.

Run docker-compose file:


""" services:
  db:
      image: postgres
      restart: always
      environment:
        POSTGRES_USER: user
        POSTGRES_PASSWORD: 
      ports:
      - "5432:5432"

  words-similiarities:
    build: ./backend/badatz-word-similarity-backend/
    ports:
      - "8080:8080"
      - "80:8080 """"

On first start, might take around 15 mintues to index the dictionary.

## Algorirthm
For each word we order the word by alphabetical order. We then save the order along side the word as key-value pair.
All words having the same key are considered similares.
When getting similar words, we simply calculate the key-word of the word and query the DB to get all values with the same key
      