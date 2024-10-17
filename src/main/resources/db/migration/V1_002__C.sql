-- Ödemeler tablosu
CREATE TABLE Payments (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    user_id INT REFERENCES Users(id) ON DELETE CASCADE,
    amount DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

-- Ödeme Yöntemleri tablosu
CREATE TABLE Payment_Methods (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(id) ON DELETE CASCADE,
    type VARCHAR(50) NOT NULL,
    details TEXT NOT NULL,
    is_default BOOLEAN DEFAULT FALSE
);

-- İşlemler tablosu
CREATE TABLE Transactions (
    id SERIAL PRIMARY KEY,
    payment_id INT REFERENCES Payments(id) ON DELETE CASCADE,
    transaction_id VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- İadeler tablosu
CREATE TABLE Refunds (
    transaction_id INT REFERENCES Transactions(id) ON DELETE CASCADE,
    amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Ödeme Kayıtları tablosu
CREATE TABLE Payment_Logs (
    id SERIAL PRIMARY KEY,
    payment_id INT REFERENCES Payments(id) ON DELETE CASCADE,
    action VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    details TEXT
);
