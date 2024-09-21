
CREATE TABLE user (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(255),
                      username VARCHAR(255),
                      password VARCHAR(255),
                      role ENUM('CLIENT', 'WORKER', 'ADMIN')
);

CREATE TABLE admin (
                       id BIGINT PRIMARY KEY,
                       FOREIGN KEY (id) REFERENCES user(id)
);

CREATE TABLE client (
                        id BIGINT PRIMARY KEY,
                        company_name VARCHAR(255),
                        FOREIGN KEY (id) REFERENCES user(id)
);

CREATE TABLE worker (
                        id BIGINT PRIMARY KEY,
                        skill ENUM('WEB_DEVELOPMENT', 'GRAPHIC_DESIGN', 'CONTENT_WRITING', 'SEO', 'DIGITAL_MARKETING',
                            'VIDEO_EDITING', 'MOBILE_APP_DEVELOPMENT', 'UI_UX_DESIGN', 'DATA_ANALYSIS',
                            'VIRTUAL_ASSISTANCE', 'TRANSLATION', 'ECOMMERCE_MANAGEMENT', 'COPYRIGHTING',
                            'WORDPRESS_DEVELOPMENT', 'SOCIAL_MEDIA_MANAGEMENT', 'VOICE_OVER', 'EMAIL_MARKETING',
                            'CUSTOMER_SUPPORT', 'ACCOUNTING', 'CLOUD_COMPUTING', 'CYBERSECURITY'),
                        balance DOUBLE,
                        experience INT,
                        FOREIGN KEY (id) REFERENCES user(id)
);

CREATE TABLE task (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(255),
                      description TEXT,
                      category ENUM('WEB_DEVELOPMENT', 'MOBILE_DEVELOPMENT', 'GRAPHIC_DESIGN', 'DIGITAL_MARKETING',
                          'CONTENT_WRITING', 'VIDEO_EDITING', 'VIRTUAL_ASSISTANCE', 'TRANSLATION',
                          'DATA_ANALYSIS', 'VOICE_OVER', 'UI_UX_DESIGN', 'SOFTWARE_DEVELOPMENT',
                          'ECOMMERCE', 'BUSINESS_CONSULTING', 'AUDIO_PRODUCTION', 'PHOTOGRAPHY'),
                      status ENUM('PENDING', 'ACCEPTED', 'IN_PROGRESS', 'COMPLETED', 'REJECTED'),
                      price DOUBLE,
                      deadline DATE,
                      client_id BIGINT,
                      FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE application (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             status ENUM('PENDING', 'ACCEPTED', 'REJECTED'),
                             task_id BIGINT,
                             worker_id BIGINT,
                             FOREIGN KEY (task_id) REFERENCES task(id),
                             FOREIGN KEY (worker_id) REFERENCES worker(id)
);

CREATE TABLE review (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        rating INT,
                        comment TEXT,
                        worker_id BIGINT,
                        client_id BIGINT,
                        FOREIGN KEY (worker_id) REFERENCES worker(id),
                        FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE payment (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         amount DOUBLE,
                         status ENUM('PENDING', 'COMPLETED', 'REFUNDED'),
                         client_id BIGINT,
                         worker_id BIGINT,
                         FOREIGN KEY (client_id) REFERENCES client(id),
                         FOREIGN KEY (worker_id) REFERENCES worker(id)
);