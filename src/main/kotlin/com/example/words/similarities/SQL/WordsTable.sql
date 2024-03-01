CREATE TABLE words (
    word_key TEXT,
    word TEXT,
    timestamp TIMESTAMP,
    PRIMARY KEY (word_key, word)
);