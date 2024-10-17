-- Kullanıcılar tablosu
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    roles VARCHAR(255) NOT NULL
);

-- API Anahtarları tablosu
CREATE TABLE API_Keys (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(id) ON DELETE CASCADE,
    api_key VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP
);

-- Rate Limiti tablosu
CREATE TABLE Rate_Limits (
    id SERIAL PRIMARY KEY,
    api_key_id INT REFERENCES API_Keys(id) ON DELETE CASCADE,
    limit_value INT NOT NULL,
    window_value INTERVAL NOT NULL
);

-- API Kayıtları tablosu
CREATE TABLE Logs (
    id SERIAL PRIMARY KEY,
    api_key_id INT REFERENCES API_Keys(id) ON DELETE CASCADE,
    endpoint VARCHAR(255) NOT NULL,
    request_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    response_time TIMESTAMP,
    status_code INT
);

-- Kimlik Doğrulama Token'ları tablosu
CREATE TABLE Authentication_Tokens (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(id) ON DELETE CASCADE,
    token VARCHAR(255) UNIQUE NOT NULL,
    issued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP
);
