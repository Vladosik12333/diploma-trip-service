--liquibase formatted sql

--changeset diploma-trip-service:2-seed-trip-categories
INSERT INTO categories (code, name, description, created_at, updated_at)
VALUES ('museum', 'Museum', 'Museums and collections open to visitors.', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('art_gallery', 'Art gallery', 'Art galleries and exhibition spaces.', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('historical_landmark', 'Historical landmark',
        'Places recognized for historical significance.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('monument', 'Monument', 'Monuments and memorial sites.', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('castle', 'Castle', 'Historic castles and castle sites.', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('cultural_landmark', 'Cultural landmark', 'Places of cultural significance.',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('park', 'Park', 'Public parks and green spaces.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('beach', 'Beach', 'Beaches and waterfront areas.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('hiking_area', 'Hiking area', 'Areas and trails suitable for hiking.', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('zoo', 'Zoo', 'Zoos and animal parks.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('tourist_attraction', 'Tourist attraction', 'Popular places and attractions for visitors.',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('church', 'Church', 'Churches and Christian places of worship.', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('mosque', 'Mosque', 'Mosques and Islamic places of worship.', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('synagogue', 'Synagogue', 'Synagogues and Jewish places of worship.', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);
