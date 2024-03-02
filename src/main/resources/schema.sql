CREATE TABLE IF NOT EXISTS api_requests (
    timestamp TIMESTAMP,
    process_time FLOAT
);

CREATE TABLE IF NOT EXISTS words (
    word_key TEXT,
    word TEXT,
    timestamp TIMESTAMP,
    PRIMARY KEY (word_key, word)
);

CREATE INDEX IF NOT EXISTS words_word_key_index
ON words(word_key);