

CREATE TABLE if not exists VACCINES
(
    ID                identity,
    researchName      varchar(50),
    manufacturerName varchar(50) ,
    type       varchar(50) ,
    numberOfShots     INTEGER   ,
    availableDoses      INTEGER   ,
    PRIMARY KEY (ID)
);



CREATE TABLE if not exists SIDEEFFECTS
(
    ID              identity,
    vaccineID       INT not null,
    description     varchar(200) not null,
    longDescription varchar(400) not null,
    frequency       FLOAT       not null,
    PRIMARY KEY (ID),
    FOREIGN KEY (vaccineID) REFERENCES VACCINES (ID)
);


CREATE TABLE if not exists user(

    ID identity,
    username varchar(100),
    password varchar(200),
    first_name varchar(200),
    last_name varchar(200)

);

CREATE TABLE if not exists authority(
    ID identity,
    name varchar(200)
);

CREATE TABLE if not exists user_authority(
    user_id bigint not null,
    authority_id bigint not null,
    constraint fk_user foreign key (user_id) references user(ID),
    constraint fk_authority foreign key (authority_id) references authority(ID)
 );
