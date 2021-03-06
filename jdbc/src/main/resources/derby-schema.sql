CREATE TABLE contact (
  id         INT         NOT NULL GENERATED ALWAYS AS IDENTITY,
  first_name VARCHAR(60) NOT NULL,
  last_name  VARCHAR(40) NOT NULL,
  birth_date DATE,
  UNIQUE (first_name, last_name),
  PRIMARY KEY (id)
);
CREATE TABLE contact_tel_detail (
  id         INT         NOT NULL GENERATED ALWAYS AS IDENTITY,
  contact_id INT         NOT NULL,
  tel_type   VARCHAR(20) NOT NULL,
  tel_number VARCHAR(20) NOT NULL,
  UNIQUE (contact_id, tel_type),
  PRIMARY KEY (id),
  CONSTRAINT fk_contact_tel_detail FOREIGN KEY (contact_id)
  REFERENCES contact (id)
);