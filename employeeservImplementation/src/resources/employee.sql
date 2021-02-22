CREATE TABLE "employee" (
  "id" INT AUTO_INCREMENT  PRIMARY KEY,
  "first_name" VARCHAR NOT NULL,
  "second_name" VARCHAR NOT NULL,
  "date_of_birth" DATE NOT NULL,
  "address_line1" VARCHAR NOT NULL,
  "address_line2" VARCHAR,
  "city" VARCHAR NOT NULL,
  "state" VARCHAR NOT NULL,
  "country" VARCHAR NOT NULL,
  "zip_code" VARCHAR NOT NULL
);