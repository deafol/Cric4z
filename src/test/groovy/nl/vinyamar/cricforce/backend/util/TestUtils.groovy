package nl.vinyamar.cricforce.backend.util

import com.google.common.base.Charsets
import com.google.common.io.Resources

class TestUtils {

    static fixture(classPathResource) {
        Resources.toString(Resources.getResource(classPathResource), Charsets.UTF_8)
    }
}
