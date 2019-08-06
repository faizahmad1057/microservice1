DROP TABLE IF EXISTS quotes;

CREATE TABLE quotes (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  userName VARCHAR(250) NOT NULL,
  quote VARCHAR(250) NOT NULL
);

INSERT INTO quotes (id, userName, quote) VALUES
  ('23', 'Sam', 'GOOG'),
  ('45', 'Peter', 'GOOG');