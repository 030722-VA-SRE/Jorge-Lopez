--------- DROP TABLE --------------
DROP TABLE IF EXIST ITEM;

--------- CREATE TABLE ------------
CREATE TABLE item (
	itemid serial NOT NULL,
	itemname varchar(30) NULL,
	hometown varchar(30) NULL,
	itemdescription varchar(40) NULL,
	CONSTRAINT item_pkey PRIMARY KEY (itemid)
);

-------- INSERT SOME DATA ------------
INSERT INTO item (itemid, itemname, hometown, itemdescription) VALUES(1, 'Naruto', 'Hidden-Leaf-Village', 'Seventh Hokage, Nine Tail Fox');
INSERT INTO item (itemid, itemname, hometown, itemdescription) VALUES(2, 'Sasuke', 'Hidden-Leaf-Village', 'Uchicha Clan');
INSERT INTO item(itemid, itemname, hometown, itemdescription) VALUES(3, 'Kakashi', 'Hidden-Leaf-Village', 'Sixth Hokage');
INSERT INTO item(itemid, itemname, hometown, itemdescription) VALUES(6, 'Itachi', 'Hidden-Leaf-Village', 'Akatsuki member');
INSERT INTO item (itemid, itemname, hometown, itemdescription) VALUES(7, 'Sasori', 'Hidden-Sand-Village', 'Akatsuki member');
INSERT INTO item (itemid, itemname, hometown, itemdescription) VALUES(9, 'Temari', 'Hidden-Sand-Village', 'Gaara sister');
INSERT INTO item (itemid, itemname, hometown, itemdescription) VALUES(4, 'Gaara', 'Hidden-Sand-Village', 'Fifth Kazekage,Naruto BF');
INSERT INTO item (itemid, itemname, hometown, itemdescription) VALUES(5, 'Sakura', 'Hidden-Leaf-Village', 'Leaf Villager');
INSERT INTO item (itemid, itemname, hometown, itemdescription) VALUES(8, 'Shikamaru', 'Hidden-Leaf-Village', 'Leaf Villager');

------- GET: Using ItemID -------
select * from item where itemid = 1;

----- GET: All items -----
select * from item;

--------- GET: Only ninjas based on hometown/village given ------------
select * from item where hometown  = 'Hidden _____ Village';

------------ GET: Only ninjas based on name given ------------
select * from item where itemname  = 'Ninja name';

--------- POST: Add ninja to database ------------------
INSERT INTO item (itemname, hometown, itemdescription) VALUES('name', 'Hidden-Blank-Village', 'descibe ninja');

----------- PUT: Update ninja based on ID --------------------
UPDATE item SET itemdescription = 'updated description' WHERE itemid = number;