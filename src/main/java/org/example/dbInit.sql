-- room 테이블 생성
CREATE TABLE room (
    room_id SERIAL PRIMARY KEY,
    type VARCHAR(255),
    cost NUMERIC
);

-- customer 테이블 생성
CREATE TABLE customer (
    customer_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    phone_number VARCHAR(15)
);

-- reservation 테이블 생성
CREATE TABLE reservation (
    reservation_id SERIAL PRIMARY KEY,
    room_id INT,
    customer_id INT,
    start_date DATE,
    end_date DATE,
    status VARCHAR(50),
    FOREIGN KEY (room_id) REFERENCES room (room_id),
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

-- check_log 테이블 생성
CREATE TABLE check_log (
    log_id SERIAL PRIMARY KEY,
    reservation_id INT,
    checkin_date DATE,
    checkout_date DATE,
    FOREIGN KEY (reservation_id) REFERENCES reservation (reservation_id)
);

-- housekeeping 테이블 생성
CREATE TABLE housekeeping (
    housekeeping_id SERIAL PRIMARY KEY,
    room_id INT,
    date DATE,
    status VARCHAR(50),
    FOREIGN KEY (room_id) REFERENCES room (room_id)
);

-- mock data 삽입
-- 방 생성
INSERT INTO room (type, cost) VALUES
                                  ('Standard', 80000),
                                  ('Standard', 80000),
                                  ('Standard', 80000),
                                  ('Deluxe', 110000),
                                  ('Deluxe', 110000),
                                  ('Suite', 170000);

-- 고객 생성
INSERT INTO customer (name, phone_number) VALUES
                                              ('Kim', '010-1543-1949'),
                                              ('Lee', '010-2478-2901'),
                                              ('Park', '010-3440-3781');

-- 오늘은 1월 4일
-- 예약 생성
INSERT INTO reservation (room_id, customer_id, start_date, end_date, status) VALUES
                                                                                 (1, 1, '2024-01-01', '2024-01-03', 'Completed'),
                                                                                 (2, 2, '2024-01-03', '2024-01-10', 'Confirmed'),
                                                                                 (6, 3, '2024-01-02', '2024-01-04', 'Confirmed');

-- 체크 데이터 생성
INSERT INTO check_log (reservation_id, checkin_date, checkout_date) VALUES
                                                                        (1, '2024-01-01', '2024-01-03'),
                                                                        (2, '2024-01-03', '2024-01-10'),
                                                                        (3, '2024-01-02', '2024-01-04');

-- 하우스키핑 생성
INSERT INTO housekeeping (room_id, date, status) VALUES
                                                     (1, '2024-01-03', 'Completed'),
                                                     (2, '2024-01-11', 'Pending'),
                                                     (6, '2024-01-04', 'Pending');


