package com.sda.study.springbootpractice;

import com.sda.study.springbootpractice.models.School;
import com.sda.study.springbootpractice.repositories.SchoolRepository;
import com.sda.study.springbootpractice.services.CourseService;
import com.sda.study.springbootpractice.services.SchoolService;
import com.sda.study.springbootpractice.services.implementations.SchoolServiceImpl;
import org.apache.logging.log4j.message.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for School Service
 *
 * @author Vinod John
 * @Date 15.03.2023
 */
@ExtendWith(MockitoExtension.class)
public class SchoolServiceTest {
    @InjectMocks
    private SchoolService schoolService = new SchoolServiceImpl();

    @Mock
    private SchoolRepository schoolRepository;

    @Mock
    private CourseService courseService;

    @Captor
    private ArgumentCaptor<Message> messageArgumentCaptor;

    @Test
    public void whenFindAllSchoolsCalled_shouldReturnSchoolList() {


        School school = new School();
        school.setId(1L);
        school.setName("Tartu Univ");
        school.setPhone("12312341324");
        school.setAddress("Tartu");
        school.setActive(true);

        School school1 = new School();
        school1.setId(2L);
        school1.setName("Tallinn Univ");
        school1.setPhone("123123asda41324");
        school1.setAddress("Tallinn");
        school1.setActive(true);

        List<School> schoolList = Arrays.asList(school, school1);

        Mockito.when(schoolRepository.findAll())
                .thenReturn(schoolList);



        List<School> resultSchool = schoolService.findAllSchools();

        Message capturedMessage = messageArgumentCaptor.capture();

        Assertions.assertEquals("", capturedMessage.getFormattedMessage());

        Assertions.assertEquals(schoolList.size(), resultSchool.size());
        Assertions.assertEquals(schoolList.get(0).getName(), resultSchool.get(0).getName());
    }
}