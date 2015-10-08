import viensdansmacave.TestSetService

class BootStrap {

    TestSetService testSetService

    def init = { servletContext ->
        testSetService.createTestSet()
    }
    def destroy = {
    }
}
