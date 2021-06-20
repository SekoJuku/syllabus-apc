CREATE TABLE "disciplines" (
                               "id" serial NOT NULL,
                               "name" varchar(255) NOT NULL,
                               "language" varchar(10) NOT NULL,
                               "credits" integer NOT NULL,
                               "lecture_hours_per_week" integer NOT NULL,
                               "practice_hours_per_week" integer NOT NULL,
                               "isw_hours_per_week" integer NOT NULL,
                               CONSTRAINT "disciplines_pk" PRIMARY KEY ("id")
);



CREATE TABLE "syllabus" (
                            "id" serial NOT NULL,
                            "discipline_id" integer NOT NULL,
                            "name" varchar(255) NOT NULL,
                            "credits" integer NOT NULL,
                            "aim" TEXT NOT NULL,
                            "tasks" TEXT NOT NULL,
                            "results" TEXT NOT NULL,
                            "methodology" TEXT NOT NULL,
                            "year" varchar(16) NOT NULL,
                            "evaluation_id" integer NOT NULL,
                            "competences" TEXT NOT NULL,
                            "rubric_id" integer NOT NULL,
                            CONSTRAINT "syllabus_pk" PRIMARY KEY ("id")
);



CREATE TABLE "syllabus_program" (
                                    "id" serial NOT NULL,
                                    "syllabus_id" integer NOT NULL,
                                    "lecture_theme" TEXT NOT NULL,
                                    "practice_theme" TEXT NOT NULL,
                                    "isw_theme" TEXT NOT NULL,
                                    "week" integer NOT NULL,
                                    CONSTRAINT "syllabus_program_pk" PRIMARY KEY ("id")
);



CREATE TABLE "users" (
                         "id" serial NOT NULL,
                         "username" varchar(128) NOT NULL,
                         "password" varchar(128) NOT NULL,
                         "role_id" integer NOT NULL,
                         CONSTRAINT "users_pk" PRIMARY KEY ("id")
);



CREATE TABLE "personal_info" (
                                 "id" serial NOT NULL,
                                 "user_id" integer NOT NULL,
                                 "sname" varchar(128) NOT NULL,
                                 "name" varchar(128) NOT NULL,
                                 "mname" varchar(128) NOT NULL,
                                 "academic_degree" varchar(255) NOT NULL,
                                 "academic_rank" varchar(255) NOT NULL,
                                 "position_id" integer NOT NULL,
                                 "email" varchar(128) NOT NULL,
                                 "phone" varchar(15) NOT NULL,
                                 "description" TEXT NOT NULL,
                                 CONSTRAINT "personal_info_pk" PRIMARY KEY ("id")
);



CREATE TABLE "comments" (
                            "id" serial NOT NULL,
                            "comment" TEXT NOT NULL,
                            "syllabus_id" integer NOT NULL,
                            "coordinator_id" integer NOT NULL,
                            CONSTRAINT "comments_pk" PRIMARY KEY ("id")
);



CREATE TABLE "instructors" (
                               "id" serial NOT NULL,
                               "user_id" integer NOT NULL,
                               "syllabus_id" integer NOT NULL,
                               CONSTRAINT "instructors_pk" PRIMARY KEY ("id")
);



CREATE TABLE "program_details" (
                                   "id" serial NOT NULL,
                                   "syllabus_program_id" integer NOT NULL,
                                   "lecture_fof" TEXT NOT NULL,
                                   "practice_fof" TEXT NOT NULL,
                                   "isw_fof" TEXT NOT NULL,
                                   "lecture_literature" TEXT NOT NULL,
                                   "practice_literature" TEXT NOT NULL,
                                   "isw_literature" TEXT NOT NULL,
                                   "lecture_assessment" TEXT NOT NULL,
                                   "practice_assessment" TEXT NOT NULL,
                                   "isw_assessment" TEXT NOT NULL,
                                   CONSTRAINT "program_details_pk" PRIMARY KEY ("id")
);



CREATE TABLE "positions" (
                             "id" serial NOT NULL,
                             "name" varchar(255) NOT NULL,
                             CONSTRAINT "positions_pk" PRIMARY KEY ("id")
);



CREATE TABLE "syllabus_params" (
                                   "id" serial NOT NULL,
                                   "syllabus_id" integer NOT NULL,
                                   "is_final" BOOLEAN NOT NULL,
                                   "is_sendable" BOOLEAN NOT NULL,
                                   "is_sent_to_coordinator" BOOLEAN NOT NULL,
                                   "is_approved_by_coordinator" BOOLEAN NOT NULL,
                                   "is_sent_to_dean" BOOLEAN NOT NULL,
                                   "is_approved_by_dean" BOOLEAN NOT NULL,
                                   "is_active" BOOLEAN NOT NULL,
                                   CONSTRAINT "syllabus_params_pk" PRIMARY KEY ("id")
);



CREATE TABLE "evaluation_system" (
                                     "id" serial NOT NULL,
                                     "name" varchar(255) NOT NULL,
                                     "description" TEXT NOT NULL,
                                     CONSTRAINT "evaluation_system_pk" PRIMARY KEY ("id")
);


CREATE TABLE "prerequisites" (
                                 "id" serial NOT NULL,
                                 "discipline_id" integer NOT NULL,
                                 "syllabus_id" integer NOT NULL,
                                 CONSTRAINT "prerequisites_pk" PRIMARY KEY ("id")
);



CREATE TABLE "postrequisites" (
                                  "id" serial NOT NULL,
                                  "discipline_id" integer NOT NULL,
                                  "syllabus_id" integer NOT NULL,
                                  CONSTRAINT "postrequisites_pk" PRIMARY KEY ("id")
);



CREATE TABLE "rubrics" (
                           "id" serial NOT NULL,
                           "name" varchar(255) NOT NULL,
                           "description" TEXT NOT NULL,
                           CONSTRAINT "rubrics_pk" PRIMARY KEY ("id")
);

CREATE TABLE "roles" (
                         "id" serial NOT NULL,
                         "name" varchar(128) NOT NULL,
                         CONSTRAINT "roles_pk" PRIMARY KEY ("id")
);





ALTER TABLE "syllabus" ADD CONSTRAINT "syllabus_fk0" FOREIGN KEY ("discipline_id") REFERENCES "disciplines"("id");
ALTER TABLE "syllabus" ADD CONSTRAINT "syllabus_fk1" FOREIGN KEY ("evaluation_id") REFERENCES "evaluation_system"("id");
ALTER TABLE "syllabus" ADD CONSTRAINT "syllabus_fk2" FOREIGN KEY ("rubric_id") REFERENCES "rubrics"("id");

ALTER TABLE "syllabus_program" ADD CONSTRAINT "syllabus_program_fk0" FOREIGN KEY ("syllabus_id") REFERENCES "syllabus"("id");


ALTER TABLE "personal_info" ADD CONSTRAINT "personal_info_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id");
ALTER TABLE "personal_info" ADD CONSTRAINT "personal_info_fk1" FOREIGN KEY ("position_id") REFERENCES "positions"("id");

ALTER TABLE "comments" ADD CONSTRAINT "comments_fk0" FOREIGN KEY ("syllabus_id") REFERENCES "syllabus"("id");
ALTER TABLE "comments" ADD CONSTRAINT "comments_fk1" FOREIGN KEY ("coordinator_id") REFERENCES "users"("id");

ALTER TABLE "instructors" ADD CONSTRAINT "instructors_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id");
ALTER TABLE "instructors" ADD CONSTRAINT "instructors_fk1" FOREIGN KEY ("syllabus_id") REFERENCES "syllabus"("id");

ALTER TABLE "program_details" ADD CONSTRAINT "program_details_fk0" FOREIGN KEY ("syllabus_program_id") REFERENCES "syllabus_program"("id");


ALTER TABLE "syllabus_params" ADD CONSTRAINT "syllabus_params_fk0" FOREIGN KEY ("syllabus_id") REFERENCES "syllabus"("id");

ALTER TABLE "prerequisites" ADD CONSTRAINT "prerequisites_fk0" FOREIGN KEY ("discipline_id") REFERENCES "disciplines"("id");
ALTER TABLE "prerequisites" ADD CONSTRAINT "prerequisites_fk1" FOREIGN KEY ("syllabus_id") REFERENCES "syllabus"("id");

ALTER TABLE "postrequisites" ADD CONSTRAINT "postrequisites_fk0" FOREIGN KEY ("discipline_id") REFERENCES "disciplines"("id");
ALTER TABLE "postrequisites" ADD CONSTRAINT "postrequisites_fk1" FOREIGN KEY ("syllabus_id") REFERENCES "syllabus"("id");


ALTER TABLE "users" ADD CONSTRAINT "users_fk1" FOREIGN KEY ("role_id") REFERENCES "roles"("id");
