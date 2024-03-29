CREATE TABLE ROOMS
(
	ID NUMBER(19,0),
    CAPACITY NUMBER(10,0),
    FLOOR NUMBER(10,0),
    ROOM_NUMBER VARCHAR2(255 CHAR),
    CONSTRAINT ROOM_PK PRIMARY KEY (ID)
);

CREATE TABLE STUDENTS
(
    ID NUMBER(19,0),
	FIRST_NAME VARCHAR2(255 CHAR),
	LAST_NAME VARCHAR2(255 CHAR),
	GENDER NUMBER(10,0),
    BIRTH_DATE DATE,
	FACULTY VARCHAR2(255 CHAR),
	SPECIALITY VARCHAR2(255 CHAR),
	COURSE NUMBER(10,0),
	GROUP_NUMBER NUMBER(10,0),
	IMAGE_PATH VARCHAR2(255 CHAR),
	ROOM_ID NUMBER(19,0),
    CONSTRAINT STUDENT_PK PRIMARY KEY (ID),
    CONSTRAINT FK_ROOM FOREIGN KEY (ROOM_ID) REFERENCES ROOMS(ID)
);

CREATE TABLE CAMPUS_USERS
(
    ID NUMBER(19,0),
	LOGIN VARCHAR2(255 CHAR),
	PASSWORD VARCHAR2(255 CHAR),
	ROLE NUMBER(10,0),
	STUDENT_ID NUMBER(19,0),
    CONSTRAINT USER_PK PRIMARY KEY (ID),
    CONSTRAINT FK_STUDENT FOREIGN KEY (STUDENT_ID) REFERENCES STUDENTS(ID)
);

CREATE TABLE EVENTS
(
    ID NUMBER(19,0),
	TITLE VARCHAR2(255 CHAR),
	BODY VARCHAR2(255 CHAR),
	STUDENT_ID NUMBER(19,0),
    CONSTRAINT EVENTS_PK PRIMARY KEY (ID),
    CONSTRAINT FK_EVENTS_STUDENT FOREIGN KEY (STUDENT_ID) REFERENCES STUDENTS(ID)
);

CREATE TABLE CAMPUS_GROUPS
(
    ID NUMBER(19,0),
	NAME VARCHAR2(255 CHAR),
	IMAGE_URL VARCHAR2(255 CHAR),
    CONSTRAINT GROUP_PK PRIMARY KEY (ID)
);

CREATE TABLE STUDENT_GROUPS
(
    STUDENT_ID NUMBER(19,0),
	GROUP_ID NUMBER(19,0),
    CONSTRAINT FK_GROUP_STUDENT FOREIGN KEY (STUDENT_ID) REFERENCES STUDENTS(ID),
    CONSTRAINT FK_STUDENT_GROUP FOREIGN KEY (GROUP_ID) REFERENCES CAMPUS_GROUPS(ID)
);

CREATE TABLE GROUP_MESSAGE
(
    ID NUMBER(19,0),
	MESSAGE VARCHAR2(255 CHAR),
	POST_TIME TIMESTAMP,
    GROUP_ID NUMBER(19,0),
    STUDENT_ID NUMBER(19,0),
    CONSTRAINT GROUP_MESSAGE_PK PRIMARY KEY (ID),
    CONSTRAINT FK_MESSAGE_GROUP FOREIGN KEY (GROUP_ID) REFERENCES CAMPUS_GROUPS(ID),
    CONSTRAINT FK_MESSAGE_STUDENT FOREIGN KEY (STUDENT_ID) REFERENCES STUDENTS(ID)
);

CREATE TABLE STUDENT_LIKES
(
    FROM_ID NUMBER(19,0),
	TO_ID NUMBER(19,0),
	POST_TIME TIMESTAMP,
    CONSTRAINT FK_SL_FROM_STUDENT FOREIGN KEY (FROM_ID) REFERENCES STUDENTS(ID),
    CONSTRAINT FK_SL_TO_STUDENT FOREIGN KEY (TO_ID) REFERENCES STUDENTS(ID)
);

CREATE TABLE MESSAGE_LIKES
(
    FROM_ID NUMBER(19,0),
	TO_ID NUMBER(19,0),
	POST_TIME TIMESTAMP,
    CONSTRAINT FK_ML_FROM_STUDENT FOREIGN KEY (FROM_ID) REFERENCES STUDENTS(ID),
    CONSTRAINT FK_ML_TO_MESSAGE FOREIGN KEY (TO_ID) REFERENCES GROUP_MESSAGE(ID)
);