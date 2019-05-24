DROP DATABASE IF EXISTS beautyDatabase;
CREATE DATABASE IF NOT EXISTS beautyDatabase;
USE beautyDatabase;

DROP TABLE IF EXISTS beauty;
CREATE TABLE beauty (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Services VARCHAR(50),
    duration INT (50),
    venue VARCHAR(50),
	description VARCHAR(50),
    location VARCHAR(50),
    price double(8, 2),
    picture VARCHAR(50)
);

INSERT INTO beauty VALUES (NULL, ' Technical Hair Stylist', 60 , ' Phoenix Hotel', 'Nail, shellac, Hair color', 'Galway', 20.00, 'hair.jpg');
INSERT INTO beauty VALUES (NULL, ' Manicure & Pedicure', 60 , ' Bodywise Clinic', 'Massagem, shellac, Hair color', 'Dublin', 30.00, 'nails.png');
INSERT INTO beauty VALUES (NULL, ' Spa & Treatments', 30 , ' Castle Resort', 'Crystal ball, shellac, Hair transplant', 'Limerick', 50.00, 'mask.jpg');
INSERT INTO beauty VALUES (NULL, ' Massage', 30 , ' The Salon Spa', 'Nail, shellac, Hair extensions', 'Cork', 30.00, 'massagem.jpg');
INSERT INTO beauty VALUES (NULL, ' Eyebrows Shape', 20 ,' Academy Plaza Hotel', 'Nail, shellac, Permanent Make up', 'Dublin', 20.00, 'room.png');
INSERT INTO beauty VALUES (NULL, ' Makeup', 30 , ' Sheraton Athlone Hotel', 'Nail, shellac, Hair color', 'Athlone', 30.00, 'makeup.jpg');
INSERT INTO beauty VALUES (NULL, ' Hair Extensions', 90 , ' Sheraton Athlone Hotel', 'Nail, shellac, Hair color', 'Athlone', 180.00, 'hair10.jpg');
INSERT INTO beauty VALUES (NULL, ' Facial Treatments', 90 , ' Bella Imagem Spa', 'Nail, shellac, Hair color', 'Dublin', 150.00, 'facial.jpg');

select* from beauty;