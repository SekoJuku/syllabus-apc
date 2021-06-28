create table test_users (
    "id" serial NOT NULL,
    "iin" TEXT NOT NULL,
    "name" TEXT,
    "sname" TEXT,
    "mname" TEXT,
    "academic_degree" TEXT,
    "academic_rank" TEXT,
    "email" TEXT,
    "phone" TEXT,
    "description" TEXT,
    "coordinator_id" integer NOT NULL,
    CONSTRAINT "test_users_pk" PRIMARY KEY("id")
);

create table test_instructors (
    "id" serial NOT NULL,
    "user_id" integer NOT NULL,
    "syllabus_id" integer NOT NULL,
    CONSTRAINT "test_instructors_pk" PRIMARY KEY("id")
);

INSERT INTO test_users(iin, name, sname, mname, academic_degree, academic_rank, email, phone, description, coordinator_id)
    VALUES ('011023550326','Serikzhan','Kuanyshev','Azamatovich','bachelor','bachelor','serikzhan@gmail.com','77777894561','description', 2);
INSERT INTO test_users(iin, name, sname, mname, academic_degree, academic_rank, email, phone, description, coordinator_id)
    VALUES ('123456789123', 'Mbappe', 'Mbappe', 'Mbappe','bachelor','bachelor', 'mbappe@gmail.com','77777777777','description',2);
