DROP TABLE IF EXISTS Rate_Limits;

-- Yeni Rate_Limits tablosunu oluştur
CREATE TABLE Rate_Limits (
    id SERIAL PRIMARY KEY,
    api_key_id INT REFERENCES API_Keys(id) ON DELETE CASCADE,
    limit_value INT NOT NULL,
    window_value INTEGER NOT NULL,  -- window_value artık INTEGER türünde (dakika cinsinden)
    created_by VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_by VARCHAR(255),
    updated_at TIMESTAMP,
    deleted_by VARCHAR(255),
    deleted_at TIMESTAMP,
    version INT DEFAULT 1
);


