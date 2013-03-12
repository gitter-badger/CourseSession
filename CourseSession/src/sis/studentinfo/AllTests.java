package sis.studentinfo;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import sis.report.RosterReporterTest;
import sis.summer.SummerCourseSessionTest;


@RunWith(Suite.class)
@SuiteClasses( { CourseSessionTest.class, 
	StudentTest.class, 
	DateUtilTest.class,
	BasicGradingStrategyTest.class,
	HonorsGradingStrategyTest.class,
	StudentDirectoryTest.class,
	ScorerTest.class,
	CourseTest.class,
	CourseSessionTest.class,
	SummerCourseSessionTest.class,
	CourseCatalog.class,
	AccountTest.class} )
public class AllTests{}
