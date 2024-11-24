package tr.edu.ogu.ceng.gateway;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tr.edu.ogu.ceng.gateway.dto.ApiKeyDto;

@SpringBootTest
@ExtendWith(MockitoExtension.class) 
public class DtoTests {

    @Test
    void testApiKeyDto() {
        // Given (Verilen)
        Long expectedId = 1L;
        String expectedApiKey = "gatewayapi";
        String expectedCreatedBy = "admin";
        String expectedUpdatedBy = "admin";
        String expectedDeletedBy = "admin";
        int expectedVersion = 1;

        // When (Ne zaman)
        ApiKeyDto apiKeyDto = new ApiKeyDto();
        apiKeyDto.setId(expectedId);
        apiKeyDto.setApiKey(expectedApiKey);
        apiKeyDto.setCreatedBy(expectedCreatedBy);
        apiKeyDto.setUpdatedBy(expectedUpdatedBy);
        apiKeyDto.setDeletedBy(expectedDeletedBy);
        apiKeyDto.setVersion(expectedVersion);

        // Then (O zaman)
        assertThat(apiKeyDto).isNotNull();
        assertThat(apiKeyDto.getId()).isEqualTo(expectedId);
        assertThat(apiKeyDto.getApiKey()).isEqualTo(expectedApiKey);
        assertThat(apiKeyDto.getCreatedBy()).isEqualTo(expectedCreatedBy);
        assertThat(apiKeyDto.getUpdatedBy()).isEqualTo(expectedUpdatedBy);
        assertThat(apiKeyDto.getDeletedBy()).isEqualTo(expectedDeletedBy);
        assertThat(apiKeyDto.getVersion()).isEqualTo(expectedVersion);
    }
}
