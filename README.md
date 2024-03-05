# badatz-word-similarity-backend

## How To Use

1. Open folder for all repos.
2. Clone backend project - https://github.com/idomarko98/badatz-word-similarity-backend
3. Clone frontend project - https://github.com/idomarko98/badatz-word-similarity-frontend
4. Create and run docker-compose file:

```services:
  db:
      image: postgres
      restart: always
      environment:
        POSTGRES_USER: user
        POSTGRES_PASSWORD: pass123
      ports:
      - "5432:5432"

  words-similiarities:
    build: ./badatz-word-similarity-backend/
    depends_on:
      - db
    ports:
      - "8080:8080"

  words-similiarities-front:
    build: ./badatz-word-similarity-frontend/
    depends_on:
      - words-similiarities
    ports:
      - 3000:3000
```

5. Run `docker-compose up` inside folder.
6. Enjoy!

On first start, might take a few mintues to index the dictionary.

## Algorirthm

For each word we order the word by alphabetical order. We then save the order along side the word as key-value pair.
All words having the same key are considered similares.
When getting similar words, we simply calculate the key-word of the word and query the DB to get all values with the same key
