CREATE TABLE "users" (
	"id" serial NOT NULL,
	"username" varchar(128) NOT NULL UNIQUE,
	"password" varchar(128) NOT NULL,
	"role_id" integer NOT NULL,
	CONSTRAINT "users_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "disciplines" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL,
	"language" varchar(4) NOT NULL,
	"credits" integer NOT NULL,
	"lecture_hours_per_week" integer NOT NULL,
	"practice_hours_per_week" integer NOT NULL,
	"isw_hours_per_week" integer NOT NULL,
	CONSTRAINT "disciplines_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
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
) WITH (
  OIDS=FALSE
);



CREATE TABLE "roles" (
	"id" serial NOT NULL,
	"name" varchar(128) NOT NULL,
	CONSTRAINT "roles_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "positions" (
	"id" serial NOT NULL,
	"name" varchar(128) NOT NULL,
	CONSTRAINT "positions_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "discipline_info" (
	"id" serial NOT NULL,
	"discipline_id" integer NOT NULL,
	"credits" integer NOT NULL,
	"aim" TEXT NOT NULL,
	"tasks" TEXT NOT NULL,
	"results" TEXT NOT NULL,
	"methodology" TEXT NOT NULL,
	CONSTRAINT "discipline_info_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "instructors" (
	"id" serial NOT NULL,
	"user_id" integer NOT NULL,
	"discipline_info_id" integer NOT NULL,
	CONSTRAINT "instructors_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "discipline_info_program" (
	"id" serial NOT NULL,
	"instructor_id" integer NOT NULL,
	"discipline_info_id" integer NOT NULL,
	"evaluation_id" integer NOT NULL,
	CONSTRAINT "discipline_info_program_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "program_info" (
	"id" serial NOT NULL,
	"program_id" integer NOT NULL,
	"lecture_theme" TEXT NOT NULL,
	"practice_theme" TEXT NOT NULL,
	"isw_theme" TEXT NOT NULL,
	"week" integer NOT NULL,
	"competencies" TEXT NOT NULL,
	CONSTRAINT "program_info_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "program_details" (
	"id" serial NOT NULL,
	"program_info_id" integer NOT NULL,
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
) WITH (
  OIDS=FALSE
);
CREATE TABLE "evaluation_systems" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL,
	"description" TEXT NOT NULL,
	CONSTRAINT "evaluation_systems_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);
CREATE TABLE "discipline_evaluation" (
	"id" serial NOT NULL,
	"evaluation_system_id" integer NOT NULL,
	"discipline_id" integer NOT NULL,
	CONSTRAINT "discipline_evaluation_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);
CREATE TABLE "prerequisites" (
	"id" serial NOT NULL,
	"discipline_id" integer NOT NULL,
	"discipline_info_id" integer NOT NULL,
	CONSTRAINT "prerequisites_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);
CREATE TABLE "postrequisites" (
	"id" serial NOT NULL,
	"discipline_id" integer NOT NULL,
	"discipline_info_id" integer NOT NULL,
	CONSTRAINT "postrequisites_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

ALTER TABLE "users" ADD CONSTRAINT "users_fk0" FOREIGN KEY ("role_id") REFERENCES "roles"("id");
ALTER TABLE "personal_info" ADD CONSTRAINT "personal_info_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id");
ALTER TABLE "personal_info" ADD CONSTRAINT "personal_info_fk1" FOREIGN KEY ("position_id") REFERENCES "positions"("id");
ALTER TABLE "discipline_info" ADD CONSTRAINT "discipline_info_fk0" FOREIGN KEY ("discipline_id") REFERENCES "disciplines"("id");
ALTER TABLE "instructors" ADD CONSTRAINT "instructors_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id");
ALTER TABLE "instructors" ADD CONSTRAINT "instructors_fk1" FOREIGN KEY ("discipline_info_id") REFERENCES "discipline_info"("id");
ALTER TABLE "discipline_info_program" ADD CONSTRAINT "discipline_info_program_fk0" FOREIGN KEY ("instructor_id") REFERENCES "instructors"("id");
ALTER TABLE "discipline_info_program" ADD CONSTRAINT "discipline_info_program_fk1" FOREIGN KEY ("discipline_info_id") REFERENCES "discipline_info"("id");
ALTER TABLE "discipline_info_program" ADD CONSTRAINT "discipline_info_program_fk2" FOREIGN KEY ("evaluation_id") REFERENCES "evaluation_systems"("id");
ALTER TABLE "program_info" ADD CONSTRAINT "program_info_fk0" FOREIGN KEY ("program_id") REFERENCES "discipline_info_program"("id");
ALTER TABLE "program_details" ADD CONSTRAINT "program_details_fk0" FOREIGN KEY ("program_info_id") REFERENCES "program_info"("id");
ALTER TABLE "discipline_evaluation" ADD CONSTRAINT "discipline_evaluation_fk0" FOREIGN KEY ("evaluation_system_id") REFERENCES "evaluation_systems"("id");
ALTER TABLE "discipline_evaluation" ADD CONSTRAINT "discipline_evaluation_fk1" FOREIGN KEY ("discipline_id") REFERENCES "disciplines"("id");
ALTER TABLE "prerequisites" ADD CONSTRAINT "prerequisites_fk0" FOREIGN KEY ("discipline_id") REFERENCES "disciplines"("id");
ALTER TABLE "prerequisites" ADD CONSTRAINT "prerequisites_fk1" FOREIGN KEY ("discipline_info_id") REFERENCES "discipline_info"("id");
ALTER TABLE "postrequisites" ADD CONSTRAINT "postrequisites_fk0" FOREIGN KEY ("discipline_id") REFERENCES "disciplines"("id");
ALTER TABLE "postrequisites" ADD CONSTRAINT "postrequisites_fk1" FOREIGN KEY ("discipline_info_id") REFERENCES "discipline_info"("id");




