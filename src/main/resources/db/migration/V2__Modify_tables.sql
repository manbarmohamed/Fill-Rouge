-- Create the company table
CREATE TABLE company (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255),
                         address VARCHAR(255),
                         phone VARCHAR(255),
                         email VARCHAR(255),
                         website VARCHAR(255),
                         description TEXT
);

-- Modify the client table
ALTER TABLE client
    ADD COLUMN company_id BIGINT,
ADD FOREIGN KEY (company_id) REFERENCES company(id);