package platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Db {

    private final CodeRepository codeRepository;

    public Db(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public void add(Code code) {
        codeRepository.save(code);
    }

    public Code get(String id) {
        Optional<Code> result = codeRepository.findById(id);

        if (result.isPresent()) {
            Code code = result.get();

            if (code.isSecret()) {
                if (chekSeconds(code) && chekViews(code)) {
                    return code;
                } else {
                    throw new CodeNotFoundException("Limits is end");
                }
            } else {
                return code;
            }
        } else {
            throw new CodeNotFoundException("Not found code by path: " + id);
        }
    }

    private boolean chekSeconds(Code code) {
        if (code.isSeconds()) {
            if (code.getSeconds() > 0) {
                return true;
            } else {
                codeRepository.delete(code);
                return false;
            }
        } else {
            return true;
        }
    }

    private boolean chekViews(Code code) {
        if (code.getViews() > 0) {
            if (code.getViews() == 1) {
                code.oneView();
                code.oneView();
                codeRepository.delete(code);
            } else {
                code.oneView();
                codeRepository.save(code);
            }
        }
        return true;
    }

    public List<Code> latest() {
        List<Code> output = new ArrayList<>();

        for (Code code : codeRepository.findFirst10BySecretOrderByUpdateDateDesc(false)) {
            output.add(code);
        }

        return output;
    }

}
