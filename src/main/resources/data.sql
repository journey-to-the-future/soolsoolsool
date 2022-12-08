insert into item(item_id, created_date, last_modified_date, company, creator_id, degree, image_url, info, is_soldout, material, name, price, size, sool_type, stock_quantity)
values (1, now(), now(), "회사1", 1, 35.0, "image1.jpg", "info1", false, "재료1", "술1", 15000, 300, "S", 100),
       (2, now(), now(), "회사2", 2, 12.0, "image2.jpg", "info2", false, "재료2", "술2", 13000, 500, "W", 100),
       (3, now(), now(), "회사3", 3, 17.0, "image3.jpg", "info3", true, "재료3", "술3", 25000, 1000, "T", 0),
       (4, now(), now(), "회사1", 1, 18.0, "image4.jpg", "info4", false, "재료4", "술4", 10000, 300, "W", 100),
       (5, now(), now(), "회사2", 2, 21.0, "image5.jpg", "info5", true, "재료5", "술5", 9000, 500, "S", 0);

insert into member (member_id, created_date, last_modified_date, city, street, zipcode, firstname, lastname, member_email, member_status, member_type, member_usable, nickname, pwd)
values (1, now(), now(), "seoul", "hongdae", "1234", "용용", "김", "yong@naver.com", "ONLINE", "ROLE_USER", null, "kingwangzzang", "abcd1234");