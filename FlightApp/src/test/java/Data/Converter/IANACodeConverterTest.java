package Data.Converter;


import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IANACodeConverterTest {

        @Test
        public void testGetIANACodeBasic() throws IOException {
            assertEquals("FRA", IANACodeConverter.getIANACode("Frankfurt am Main")) ;
            assertEquals("IST", IANACodeConverter.getIANACode("Istanbul")) ;
            assertEquals("SOF", IANACodeConverter.getIANACode("Sofia")) ;

        }

        @Test
        public void testGetIANACodeSpecial() throws IOException {
            String randomString = "sidjx";
            for (int i = 0; i < 10; i++) {
                assertEquals(randomString, IANACodeConverter.getIANACode(randomString));
                randomString += 3;
            }
        }

        @Test
        public void testGetIANAtoCity() throws IOException {
            assertEquals("Frankfurt am Main", IANACodeConverter.IANAToCity("FRA")) ;
            assertEquals("Istanbul", IANACodeConverter.IANAToCity("IST")) ;
            assertEquals("Sofia", IANACodeConverter.IANAToCity("SOF")) ;
            assertEquals("Munich", IANACodeConverter.IANAToCity("MUC"));

        }

    }

