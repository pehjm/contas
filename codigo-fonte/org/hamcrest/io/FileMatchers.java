/*
 * Decompiled with CFR 0.152.
 */
package org.hamcrest.io;

import java.io.File;
import java.io.IOException;
import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsEqual;

public final class FileMatchers {
    public static final FileStatus CAN_WRITE = new FileStatus(){

        @Override
        public boolean check(File actual) {
            return actual.canWrite();
        }
    };
    public static final FileStatus CAN_READ = new FileStatus(){

        @Override
        public boolean check(File actual) {
            return actual.canRead();
        }
    };
    public static final FileStatus IS_FILE = new FileStatus(){

        @Override
        public boolean check(File actual) {
            return actual.isFile();
        }
    };
    public static final FileStatus IS_DIRECTORY = new FileStatus(){

        @Override
        public boolean check(File actual) {
            return actual.isDirectory();
        }
    };
    public static final FileStatus EXISTS = new FileStatus(){

        @Override
        public boolean check(File actual) {
            return actual.exists();
        }
    };

    public static Matcher<File> anExistingDirectory() {
        return FileMatchers.fileChecker(IS_DIRECTORY, "an existing directory", "is not a directory");
    }

    public static Matcher<File> anExistingFileOrDirectory() {
        return FileMatchers.fileChecker(EXISTS, "an existing file or directory", "does not exist");
    }

    public static Matcher<File> anExistingFile() {
        return FileMatchers.fileChecker(IS_FILE, "an existing File", "is not a file");
    }

    public static Matcher<File> aReadableFile() {
        return FileMatchers.fileChecker(CAN_READ, "a readable File", "cannot be read");
    }

    public static Matcher<File> aWritableFile() {
        return FileMatchers.fileChecker(CAN_WRITE, "a writable File", "cannot be written to");
    }

    public static Matcher<File> aFileWithSize(long size) {
        return FileMatchers.aFileWithSize(IsEqual.equalTo(size));
    }

    public static Matcher<File> aFileWithSize(Matcher<Long> expected) {
        return new FeatureMatcher<File, Long>(expected, "A file with size", "size"){

            @Override
            protected Long featureValueOf(File actual) {
                return actual.length();
            }
        };
    }

    public static Matcher<File> aFileNamed(Matcher<String> expected) {
        return new FeatureMatcher<File, String>(expected, "A file with name", "name"){

            @Override
            protected String featureValueOf(File actual) {
                return actual.getName();
            }
        };
    }

    public static Matcher<File> aFileWithCanonicalPath(Matcher<String> expected) {
        return new FeatureMatcher<File, String>(expected, "A file with canonical path", "path"){

            @Override
            protected String featureValueOf(File actual) {
                try {
                    return actual.getCanonicalPath();
                }
                catch (IOException e) {
                    return "Exception: " + e.getMessage();
                }
            }
        };
    }

    public static Matcher<File> aFileWithAbsolutePath(Matcher<String> expected) {
        return new FeatureMatcher<File, String>(expected, "A file with absolute path", "path"){

            @Override
            protected String featureValueOf(File actual) {
                return actual.getAbsolutePath();
            }
        };
    }

    private static Matcher<File> fileChecker(final FileStatus fileStatus, final String successDescription, final String failureDescription) {
        return new TypeSafeDiagnosingMatcher<File>(){

            @Override
            public boolean matchesSafely(File actual, Description mismatchDescription) {
                boolean result = fileStatus.check(actual);
                if (!result) {
                    mismatchDescription.appendText(failureDescription);
                }
                return result;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(successDescription);
            }
        };
    }

    public static interface FileStatus {
        public boolean check(File var1);
    }
}

