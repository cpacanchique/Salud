package org.sectorsalud.sectorsalud.api.dto;

import lombok.Data;
import java.util.Map;
@Data
public class CreateDocumentDTO {

    private String cedula;     // paciente
    private String typeName;
    private String fileUrl;
    private Map<String, Object> metadata;
}