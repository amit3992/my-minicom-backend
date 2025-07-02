INSERT INTO users (email, created_at, updated_at) VALUES ('alice1@example.com', '2025-06-30 20:41:00', '2025-06-30 20:41:00');
INSERT INTO users (email, created_at, updated_at) VALUES ('bob2@example.com', '2025-06-30 20:41:01', '2025-06-30 20:41:01');
INSERT INTO users (email, created_at, updated_at) VALUES ('carol3@example.com', '2025-06-30 20:41:02', '2025-06-30 20:41:02');
INSERT INTO users (email, created_at, updated_at) VALUES ('dave4@example.com', '2025-06-30 20:41:03', '2025-06-30 20:41:03');
INSERT INTO users (email, created_at, updated_at) VALUES ('eve5@example.com', '2025-06-30 20:41:04', '2025-06-30 20:41:04');
INSERT INTO users (email, created_at, updated_at) VALUES ('frank6@example.com', '2025-06-30 20:41:05', '2025-06-30 20:41:05');
INSERT INTO users (email, created_at, updated_at) VALUES ('grace7@example.com', '2025-06-30 20:41:06', '2025-06-30 20:41:06');
INSERT INTO users (email, created_at, updated_at) VALUES ('heidi8@example.com', '2025-06-30 20:41:07', '2025-06-30 20:41:07');
INSERT INTO users (email, created_at, updated_at) VALUES ('ivan9@example.com', '2025-06-30 20:41:08', '2025-06-30 20:41:08');
INSERT INTO users (email, created_at, updated_at) VALUES ('judy10@example.com', '2025-06-30 20:41:09', '2025-06-30 20:41:09');
INSERT INTO users (email, created_at, updated_at) VALUES ('mallory11@example.com', '2025-06-30 20:41:10', '2025-06-30 20:41:10');
INSERT INTO users (email, created_at, updated_at) VALUES ('nick12@example.com', '2025-06-30 20:41:11', '2025-06-30 20:41:11');
INSERT INTO users (email, created_at, updated_at) VALUES ('olivia13@example.com', '2025-06-30 20:41:12', '2025-06-30 20:41:12');
INSERT INTO users (email, created_at, updated_at) VALUES ('peggy14@example.com', '2025-06-30 20:41:13', '2025-06-30 20:41:13');
INSERT INTO users (email, created_at, updated_at) VALUES ('quinn15@example.com', '2025-06-30 20:41:14', '2025-06-30 20:41:14');
INSERT INTO users (email, created_at, updated_at) VALUES ('ruth16@example.com', '2025-06-30 20:41:15', '2025-06-30 20:41:15');
INSERT INTO users (email, created_at, updated_at) VALUES ('sybil17@example.com', '2025-06-30 20:41:16', '2025-06-30 20:41:16');
INSERT INTO users (email, created_at, updated_at) VALUES ('trent18@example.com', '2025-06-30 20:41:17', '2025-06-30 20:41:17');
INSERT INTO users (email, created_at, updated_at) VALUES ('ursula19@example.com', '2025-06-30 20:41:18', '2025-06-30 20:41:18');
INSERT INTO users (email, created_at, updated_at) VALUES ('victor20@example.com', '2025-06-30 20:41:19', '2025-06-30 20:41:19');

-- MESSAGES FOR USER 1
INSERT INTO messages (user_id, content, message_read, created_at, updated_at) VALUES
                                                                                  (1, 'How do I authenticate with the Intercom API?', false, '2025-06-30 20:45:01', '2025-06-30 20:45:01'),
                                                                                  (1, 'What is the rate limit for Intercom API calls?', true, '2025-06-30 20:45:02', '2025-06-30 20:45:02'),
                                                                                  (1, 'How can I list all users via the API?', false, '2025-06-30 20:45:03', '2025-06-30 20:45:03'),
                                                                                  (1, 'Is there a way to update user attributes?', true, '2025-06-30 20:45:04', '2025-06-30 20:45:04'),
                                                                                  (1, 'How do I send a message to a user using the API?', false, '2025-06-30 20:45:05', '2025-06-30 20:45:05'),
                                                                                  (1, 'Can I retrieve conversation history via API?', true, '2025-06-30 20:45:06', '2025-06-30 20:45:06'),
                                                                                  (1, 'What scopes are required for admin endpoints?', false, '2025-06-30 20:45:07', '2025-06-30 20:45:07'),
                                                                                  (1, 'How do I use webhooks with Intercom?', true, '2025-06-30 20:45:08', '2025-06-30 20:45:08'),
                                                                                  (1, 'Is there a sandbox for testing API integrations?', false, '2025-06-30 20:45:09', '2025-06-30 20:45:09'),
                                                                                  (1, 'This documentation is outdated and sucks?', true, '2025-06-30 20:45:10', '2025-06-30 20:45:10');

-- MESSAGES FOR USER 2
INSERT INTO messages (user_id, content, message_read, created_at, updated_at) VALUES
                                                                                  (2, 'How do I authenticate with the Intercom API?', false, '2025-06-30 20:46:01', '2025-06-30 20:46:01'),
                                                                                  (2, 'What is the rate limit for Intercom API calls?', true, '2025-06-30 20:46:02', '2025-06-30 20:46:02'),
                                                                                  (2, 'How can I list all users via the API?', false, '2025-06-30 20:46:03', '2025-06-30 20:46:03'),
                                                                                  (2, 'Is there a way to update user attributes?', true, '2025-06-30 20:46:04', '2025-06-30 20:46:04'),
                                                                                  (2, 'How do I send a message to a user using the API?', false, '2025-06-30 20:46:05', '2025-06-30 20:46:05'),
                                                                                  (2, 'Can I retrieve conversation history via API?', true, '2025-06-30 20:46:06', '2025-06-30 20:46:06'),
                                                                                  (2, 'What scopes are required for admin endpoints?', false, '2025-06-30 20:46:07', '2025-06-30 20:46:07'),
                                                                                  (2, 'How do I use webhooks with Intercom?', true, '2025-06-30 20:46:08', '2025-06-30 20:46:08'),
                                                                                  (2, 'Is there a sandbox for testing API integrations?', false, '2025-06-30 20:46:09', '2025-06-30 20:46:09'),
                                                                                  (2, 'Where can I find the API changelog?', true, '2025-06-30 20:46:10', '2025-06-30 20:46:10');
