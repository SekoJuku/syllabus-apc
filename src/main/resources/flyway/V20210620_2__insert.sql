INSERT INTO public.disciplines ( name, language, credits, lecture_hours_per_week, practice_hours_per_week,
                                isw_hours_per_week)
VALUES ( 'Advanced Java1', 'English', 6, 2, 4, 3);

INSERT INTO public.disciplines ( name, language, credits, lecture_hours_per_week, practice_hours_per_week,
                                isw_hours_per_week)
VALUES ( 'C++ basics', 'English', 5, 2, 3, 2);

INSERT INTO public.disciplines ( name, language, credits, lecture_hours_per_week, practice_hours_per_week,
                                isw_hours_per_week)
VALUES ('Cryptography', 'English', 5, 2, 3, 3);

INSERT INTO public.disciplines ( name, language, credits, lecture_hours_per_week, practice_hours_per_week,
                                isw_hours_per_week)
VALUES ('История Казахстана', 'Russian', 5, 3, 2, 2);

INSERT INTO public.disciplines ( name, language, credits, lecture_hours_per_week, practice_hours_per_week,
                                isw_hours_per_week)
VALUES ('Курс казахского языка', 'Kazakh', 5, 1, 4, 3);







INSERT INTO public.evaluation_system ( name, description)
VALUES ('Schema1', '30%mid+30%mid+40%final');

INSERT INTO public.evaluation_system ( name, description)
VALUES ('Schema2', '20%mid+30%mid+50%final');

INSERT INTO public.evaluation_system ( name, description)
VALUES ('Schema3', '35%mid+35%mid+30%final');

INSERT INTO public.evaluation_system ( name, description)
VALUES ('Schema4', '40%mid+30%mid+30%final');

INSERT INTO public.evaluation_system ( name, description)
VALUES ('Schema5', '45%mid+45%mid+10%final');






INSERT INTO public.rubrics ( name, description)
VALUES ( 'Schema1', 'Description for id1');

INSERT INTO public.rubrics ( name, description)
VALUES ( 'Schame2', 'Description for id2');

INSERT INTO public.rubrics ( name, description)
VALUES ( 'Schema3', 'Description for id3');

INSERT INTO public.rubrics ( name, description)
VALUES ( 'Schema4', 'Description for id4');

INSERT INTO public.rubrics ( name, description)
VALUES ( 'Schame5 ', 'Description for id5');










INSERT INTO public.syllabus ( discipline_id, name, credits, aim, tasks, results, methodology, year, evaluation_id,
                             competences, rubric_id)
VALUES ( 1, 'Advanced Java1', 6, 'Become professional in Java', '1. assignment on Spring JPA, 2.task on Hibernate',
        'Mask as high as possible', 'Robert Martin books', '2021', 1, 'Submit assignments', 1);

INSERT INTO public.syllabus ( discipline_id, name, credits, aim, tasks, results, methodology, year, evaluation_id,
                             competences, rubric_id)
VALUES ( 1, 'Advanced Java1', 6, 'Become professional in Java', '1. assignment on Spring JPA',
        'Mask as high as possible', 'Robert Martin books', '2020', 2, 'Submit assignments', 1);

INSERT INTO public.syllabus ( discipline_id, name, credits, aim, tasks, results, methodology, year, evaluation_id,
                             competences, rubric_id)
VALUES ( 2, 'C++ basics', 5, 'Learn C++ basics', 'tasks for topic 1 ', 'At the end of the course will be available',
        'Simple text', '2021', 3, 'Default competence', 2);

INSERT INTO public.syllabus ( discipline_id, name, credits, aim, tasks, results, methodology, year, evaluation_id,
                             competences, rubric_id)
VALUES ( 2, 'Cryptography', 5, 'Learn cryptography', 'Read theory, do tasks ', 'Good knowlege',
        'Theory book blockchain', '2021', 2, 'RSA, MD5 hashing ', 2);

INSERT INTO public.syllabus ( discipline_id, name, credits, aim, tasks, results, methodology, year, evaluation_id,
                             competences, rubric_id)
VALUES ( 3, 'История Казахстана', 5, 'Изучить исторические события ', 'Прочитать книги о ханах и батырах',
        'Результаты будет в конце курса', 'Простой текст ', '2021', 3, 'Много теорий, умерренно практики', 3);

INSERT INTO public.syllabus ( discipline_id, name, credits, aim, tasks, results, methodology, year, evaluation_id,
                             competences, rubric_id)
VALUES ( 4, 'Курс казахского языка', 5, 'Изучить казахский язык', 'Задания будут даны позже',
        'Здесь будут результаты', 'Методология находится на разборе ', '2020', 2, 'Все на хорошем уровне ', 4);







INSERT INTO public.syllabus_program ( syllabus_id, lecture_theme, practice_theme, isw_theme, week)
VALUES ( 1, 'Dispatcher servlete', 'Hibernaet', 'Connection library in Java', 1);

INSERT INTO public.syllabus_program ( syllabus_id, lecture_theme, practice_theme, isw_theme, week)
VALUES ( 1, 'OOP excepton handlers', 'Solid principles', 'Dependecy injection', 2);

INSERT INTO public.syllabus_program ( syllabus_id, lecture_theme, practice_theme, isw_theme, week)
VALUES ( 2, 'Loops in C++', 'Classes ', 'Answer to question', 3);

INSERT INTO public.syllabus_program ( syllabus_id, lecture_theme, practice_theme, isw_theme, week)
VALUES ( 2, 'Arrays ', 'Objects', 'Prepare for quiz ', 4);

INSERT INTO public.syllabus_program ( syllabus_id, lecture_theme, practice_theme, isw_theme, week)
VALUES ( 2, 'Double arrays', 'Lists in use ', 'List vs Vector', 5);

INSERT INTO public.syllabus_program ( syllabus_id, lecture_theme, practice_theme, isw_theme, week)
VALUES ( 3, 'Саки и гуны', 'Абылай хан ', 'Прочитать мудрости Абая', 2);








INSERT INTO public.syllabus_params ( syllabus_id, is_final, is_sendable, is_sent_to_coordinator,
                                    is_approved_by_coordinator, is_sent_to_dean, is_approved_by_dean, is_active)
VALUES ( 1, false, false, false, false, false, false, false);

INSERT INTO public.syllabus_params ( syllabus_id, is_final, is_sendable, is_sent_to_coordinator,
                                    is_approved_by_coordinator, is_sent_to_dean, is_approved_by_dean, is_active)
VALUES ( 2, false, false, false, false, false, false, false);

INSERT INTO public.syllabus_params ( syllabus_id, is_final, is_sendable, is_sent_to_coordinator,
                                    is_approved_by_coordinator, is_sent_to_dean, is_approved_by_dean, is_active)
VALUES ( 3, false, false, false, false, false, false, false);

INSERT INTO public.syllabus_params ( syllabus_id, is_final, is_sendable, is_sent_to_coordinator,
                                    is_approved_by_coordinator, is_sent_to_dean, is_approved_by_dean, is_active)
VALUES ( 4, false, false, false, false, false, false, false);

INSERT INTO public.syllabus_params ( syllabus_id, is_final, is_sendable, is_sent_to_coordinator,
                                    is_approved_by_coordinator, is_sent_to_dean, is_approved_by_dean, is_active)
VALUES ( 5, false, false, false, false, false, false, false);





INSERT INTO public.program_details ( syllabus_program_id, lecture_fof, practice_fof, isw_fof, lecture_literature,
                                    practice_literature, isw_literature, lecture_assessment, practice_assessment,
                                    isw_assessment)
VALUES ( 1, 'lecture_fof1', 'practice fof number 1', 'Independent student work 1', 'lecture literature number 1',
        'Literature for practice 1', 'Independent student work literature 1', 'First lecture assessment',
        'Practice assessment 1', 'ISW assessment 1');

INSERT INTO public.program_details ( syllabus_program_id, lecture_fof, practice_fof, isw_fof, lecture_literature,
                                    practice_literature, isw_literature, lecture_assessment, practice_assessment,
                                    isw_assessment)
VALUES ( 2, 'lecture_fof2', 'practice fof number 2', 'Independent student work 2', 'lecture literature number 2',
        'Literature for practice 2', 'Independent student work literature 2', 'Second lecture assessment',
        'Practice assessment 2', 'ISW assessment 2');

INSERT INTO public.program_details ( syllabus_program_id, lecture_fof, practice_fof, isw_fof, lecture_literature,
                                    practice_literature, isw_literature, lecture_assessment, practice_assessment,
                                    isw_assessment)
VALUES ( 4, 'lecture_fof5', 'practice fof number 5', 'Independent student work 5', 'lecture literature number 5',
        'Literature for practice 5', 'Independent student work literature 5', 'Fifth lecture assessment',
        'Practice assessment 5', 'ISW assessment 4');

INSERT INTO public.program_details ( syllabus_program_id, lecture_fof, practice_fof, isw_fof, lecture_literature,
                                    practice_literature, isw_literature, lecture_assessment, practice_assessment,
                                    isw_assessment)
VALUES ( 3, 'lecture_fof4', 'practice fof number 4', 'Independent student work 4', 'lecture literature number 4',
        'Literature for practice 4', 'Independent student work literature 4', 'Fourth lecture assessement',
        'Practice assessment 4', 'ISW assessment 3');






INSERT INTO public.positions ( name)
VALUES ( 'dean');

INSERT INTO public.positions ( name)
VALUES ( 'dean deputy');

INSERT INTO public.positions ( name)
VALUES ( 'coordinator');

INSERT INTO public.positions ( name)
VALUES ( 'Senior lecturer');

INSERT INTO public.positions ( name)
VALUES ( 'Senior lecturer');

INSERT INTO public.positions ( name)
VALUES ( 'Lecturer');

INSERT INTO public.positions ( name)
VALUES ( 'Lecturer');

INSERT INTO public.positions ( name)
VALUES ( 'Student');





INSERT INTO public.roles ( name)
VALUES ( 'dean');

INSERT INTO public.roles ( name)
VALUES ( 'coordinator');

INSERT INTO public.roles ( name)
VALUES ( 'teacher');

INSERT INTO public.roles ( name)
VALUES ( 'student ');







INSERT INTO public.users ( username, password, role_id)
VALUES ( 'msergaziev', 'dean_pass', 1);

INSERT INTO public.users ( username, password, role_id)
VALUES ( 'dyedilkhan', 'coordinator_pass', 2);

INSERT INTO public.users ( username, password, role_id)
VALUES ( 'tmukatayev', 'tleu_pass', 3);

INSERT INTO public.users ( username, password, role_id)
VALUES ( 'otulzhanov ', 'olki_pass', 3);

INSERT INTO public.users ( username, password, role_id)
VALUES ( 'nassanova', 'test1', 3);

INSERT INTO public.users ( username, password, role_id)
VALUES ( 'dganiuly', 'test2', 4);

INSERT INTO public.users ( username, password, role_id)
VALUES ( 'zsaginov', 'pass1', 4);

INSERT INTO public.users ( username, password, role_id)
VALUES ( 'akazmagambetov', 'pass2', 4);

INSERT INTO public.users ( username, password, role_id)
VALUES ( 'skuanyshev', 'secretpass', 4);

INSERT INTO public.users ( username, password, role_id)
VALUES ( 'nshayakhmetov', 'bestpassword', 3);



INSERT INTO public.personal_info ( user_id, sname, name, mname, academic_degree, academic_rank, position_id, email,
                                  phone, description)
VALUES ( 1, 'Sergaziev', 'Muslim', 'Zhaksylykovich', 'PhD', 'Senior Professor', 1, 'm.sergaziev@astanait.edu.kz',
        '+788324285254', 'Test 1 description');

INSERT INTO public.personal_info ( user_id, sname, name, mname, academic_degree, academic_rank, position_id, email,
                                  phone, description)
VALUES ( 2, 'Yedilkhan', 'Didar', 'Yedilkhanovich', 'PhD', 'Professor', 3, 'd.yedilkhan@astanait.edu.kz',
        '+92482858335', 'Description 2 test ');

INSERT INTO public.personal_info ( user_id, sname, name, mname, academic_degree, academic_rank, position_id, email,
                                  phone, description)
VALUES ( 3, 'Mukatayev', 'Tleuzhan', 'Tleuzhanovich', 'Master', 'Master of science', 4, 't.mukatayev@astanait.edu.kz',
        '+2452489542', 'Keep in mind');

INSERT INTO public.personal_info ( user_id, sname, name, mname, academic_degree, academic_rank, position_id, email,
                                  phone, description)
VALUES ( 4, 'Tulzhanov', 'Olzhas', 'Olzhasovich', 'Master', 'Master of science', 5, 'o.tulzhanov@astanait.edu.kz',
        '+848292524', 'Description for test');

INSERT INTO public.personal_info ( user_id, sname, name, mname, academic_degree, academic_rank, position_id, email,
                                  phone, description)
VALUES ( 5, 'Assanova', 'Nurgul', 'Assnovna', 'Master', 'Master of science', 5, 'n.assanova@astanait.edu.kz',
        '+9248287524', 'Save the data ');

INSERT INTO public.personal_info ( user_id, sname, name, mname, academic_degree, academic_rank, position_id, email,
                                  phone, description)
VALUES ( 6, 'Ganiuly', 'Daniyal', 'Ganiuly', 'Student ', 'Student ', 8, 'd.ganiuly@astanait.edu.kz', '+136957237',
        'Simple text');

INSERT INTO public.personal_info ( user_id, sname, name, mname, academic_degree, academic_rank, position_id, email,
                                  phone, description)
VALUES ( 7, 'Saginov', 'Zhandos', 'Serikovich', 'Student ', 'Student ', 8, 'z.saginov@astanait.edu.kz', '+2984892598',
        'Hello world!');

INSERT INTO public.personal_info ( user_id, sname, name, mname, academic_degree, academic_rank, position_id, email,
                                  phone, description)
VALUES ( 8, 'Kazmagambetov', 'Alim', 'Alimzhanuly', 'Student ', 'Student ', 8, 'a.kazmagambetov@astanait.edu.kz',
        '+35634598634', 'Get the text ');






INSERT INTO public.instructors ( user_id, syllabus_id)
VALUES ( 3, 1);


INSERT INTO public.instructors ( user_id, syllabus_id)
VALUES ( 3, 2);

INSERT INTO public.instructors ( user_id, syllabus_id)
VALUES ( 4, 1);


INSERT INTO public.instructors ( user_id, syllabus_id)
VALUES ( 5, 2);

INSERT INTO public.instructors ( user_id, syllabus_id)
VALUES ( 5, 1);

INSERT INTO public.instructors ( user_id, syllabus_id)
VALUES ( 5, 3);




INSERT INTO public.comments ( comment, syllabus_id, coordinator_id) VALUES ( 'make is better', 1, 2);
INSERT INTO public.comments ( comment, syllabus_id, coordinator_id) VALUES ( 'This is good', 2, 2);
INSERT INTO public.comments ( comment, syllabus_id, coordinator_id) VALUES ( 'Best syllabus!', 3, 2);
INSERT INTO public.comments ( comment, syllabus_id, coordinator_id) VALUES ( 'Need to fix the last part', 4, 2);
INSERT INTO public.comments ( comment, syllabus_id, coordinator_id) VALUES ( 'Looks ok, please check again', 5, 2);



INSERT INTO public.postrequisites ( discipline_id, syllabus_id)
VALUES (1, 1);

INSERT INTO public.postrequisites ( discipline_id, syllabus_id)
VALUES (2, 2);

INSERT INTO public.postrequisites ( discipline_id, syllabus_id)
VALUES (3, 3);

INSERT INTO public.postrequisites ( discipline_id, syllabus_id)
VALUES (4, 1);

INSERT INTO public.postrequisites ( discipline_id, syllabus_id)
VALUES (2, 3);

INSERT INTO public.postrequisites ( discipline_id, syllabus_id)
VALUES (5, 4);





INSERT INTO public.prerequisites ( discipline_id, syllabus_id)
VALUES ( 1, 1);

INSERT INTO public.prerequisites ( discipline_id, syllabus_id)
VALUES ( 2, 2);

INSERT INTO public.prerequisites ( discipline_id, syllabus_id)
VALUES ( 3, 4);

INSERT INTO public.prerequisites ( discipline_id, syllabus_id)
VALUES ( 4, 5);

INSERT INTO public.prerequisites ( discipline_id, syllabus_id)
VALUES ( 5, 3);

INSERT INTO public.prerequisites ( discipline_id, syllabus_id)
VALUES ( 5, 5);

INSERT INTO public.prerequisites ( discipline_id, syllabus_id)
VALUES ( 4, 4);
