package sd.assignment.Service.Utils;

import com.lambdaworks.crypto.SCryptUtil;
import org.springframework.security.crypto.password.PasswordEncoder;


public class Encoder implements PasswordEncoder {
    private static final int N = 16384;
    private static final int r = 8;
    private static final int p = 1;

    /**
     *   The output of SCryptUtil.scrypt is a string in the modified MCF format:
     *
     *   $s0$params$salt$key
     *
     *   s0     - version 0 of the format with 128-bit salt and 256-bit derived key
     *   params - 32-bit hex integer containing log2(N) (16 bits), r (8 bits), and p (8 bits)
     *   salt   - base64-encoded salt
     *   key    - base64-encoded derived key
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return SCryptUtil.scrypt((String) rawPassword, N, r, p);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return SCryptUtil.check((String) rawPassword, encodedPassword);
    }
}