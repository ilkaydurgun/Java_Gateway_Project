-- Ödeme Kayıtları tablosunu sil
DROP TABLE IF EXISTS Payment_Logs CASCADE;

-- İadeler tablosunu sil
DROP TABLE IF EXISTS Refunds CASCADE;

-- İşlemler tablosunu sil
DROP TABLE IF EXISTS Transactions CASCADE;

-- Ödeme Yöntemleri tablosunu sil
DROP TABLE IF EXISTS Payment_Methods CASCADE;

-- Ödemeler tablosunu sil
DROP TABLE IF EXISTS Payments CASCADE;

-- Kimlik Doğrulama Token'ları tablosunu sil
DROP TABLE IF EXISTS Authentication_Tokens CASCADE;

-- API Kayıtları tablosunu sil
DROP TABLE IF EXISTS Logs CASCADE;

-- Rate Limiti tablosunu sil
DROP TABLE IF EXISTS Rate_Limits CASCADE;

-- API Anahtarları tablosunu sil
DROP TABLE IF EXISTS API_Keys CASCADE;

-- Kullanıcılar tablosunu sil
DROP TABLE IF EXISTS Users CASCADE;

-- Ayarlar tablosunu sil
DROP TABLE IF EXISTS Settings CASCADE;
