package ma.emsi.lahlou.tp1lahlou.llm;

import java.io.Serializable;

public record LlmInteraction(String questionJson, String reponseJson, String reponseExtraite)
        implements Serializable {
}
