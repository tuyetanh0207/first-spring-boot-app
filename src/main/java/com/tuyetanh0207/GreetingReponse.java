package com.tuyetanh0207;

import java.util.List;

public record GreetingReponse(
        String greet,
        List<String> favProgrammingLanguages,
        Person person
) {

}
