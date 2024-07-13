INSERT INTO tags (name)
VALUES ('Work'),
       ('Personal'),
       ('Travel'),
       ('Fitness'),
       ('Cooking'),
       ('Study');

INSERT INTO notes (title, content, user_id, created_at)
VALUES ('Meeting Notes', 'Discussed project milestones and deadlines for Q3.', 1, '2024-07-01 10:00:00+00'),
       ('Grocery List', 'Milk, Bread, Eggs, Butter, Cheese, Spinach, Tomatoes', 1, '2024-07-02 11:00:00+00'),
       ('Project Ideas', 'Brainstorming session: Mobile app for tracking fitness goals.', 1, '2024-07-03 12:00:00+00'),
       ('Travel Itinerary', 'Flight to New York on July 15, hotel booked at Hilton.', 1, '2024-07-04 13:00:00+00'),
       ('Book Summary', 'Summary of "Atomic Habits" by James Clear.', 1, '2024-07-05 14:00:00+00'),
       ('Workout Plan', 'Weekly workout plan: Monday - Chest, Tuesday - Back, Wednesday - Legs.', 1,
        '2024-07-06 15:00:00+00'),
       ('Recipe', 'Pasta Carbonara: Ingredients and cooking instructions.', 1, '2024-07-07 16:00:00+00'),
       ('To-Do List', '1. Finish report, 2. Call mom, 3. Schedule dentist appointment.', 1, '2024-07-08 17:00:00+00'),
       ('Event Planning', 'Plan for company annual party: Venue, catering, guest list.', 1, '2024-07-09 18:00:00+00'),
       ('Study Notes', 'Notes on chapter 3 of Data Structures and Algorithms.', 1, '2024-07-10 19:00:00+00');


INSERT INTO notes_tags (note_id, tag_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 1),
       (3, 2),
       (4, 3),
       (5, 6),
       (5, 1),
       (5, 2),
       (6, 4),
       (7, 5),
       (7, 2),
       (8, 2),
       (9, 1),
       (10, 6);