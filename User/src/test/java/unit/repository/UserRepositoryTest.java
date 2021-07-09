package unit.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import user.model.User;
import user.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void beforeEach() {

        userRepository = new UserRepository();
    }

    @Test
    public void getUser() {

        //GIVEN
        String username = "userNameTest";
        User user = Mockito.mock(User.class);

        //WHEN
        Mockito.when(user.getUserName()).thenReturn(username);

        userRepository.addUser(user);

        //THEN
        Assert.assertSame(userRepository.getUser(username), user);
    }

    @Test
    public void getAllUser() {

        //GIVEN
        String username = "userNameTest";
        User user = Mockito.mock(User.class);

        //WHEN
        Mockito.when(user.getUserName()).thenReturn(username);

        userRepository.addUser(user);

        //THEN
        Assert.assertTrue(userRepository.getAllUser().contains(user));
    }
}
