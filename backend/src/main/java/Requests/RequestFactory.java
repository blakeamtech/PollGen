package Requests;

import Interfaces.Request;
import Requests.objects.*;
import Requests.objects.TokenRequest;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;

/**
 * The request Factory has static methods which are used to instantiate Requests.
 * This class is not instantiable, and the private constructor is set to throw an error if it is ever used.
 */
public class RequestFactory {

    private RequestFactory() throws NoPermissionException {
        throw new NoPermissionException();
    }

    /**
     * Possible request types
     */
    public enum REQUEST_TYPE{
        RESULTS,
        DETAILS,
        VOTES,
        RELEASE,
        UNRELEASE,
        CLEAR,
        CLOSE,
        CREATE,
        UPDATE,
        RUN,
        STATE,
        ACCESS,
        POLLS,
        AUTHENTICATION,
        SIGNUP,
        TOKEN,
        CHANGE,
        FORGOT
    }

    /**
     * Method which parses the a get Request path, and instantiates a Request type from it .
     * The request type is then used to return an instance of a certain Request.
     * There is default InvalidRequest returned if ever there is an unhandled request path passed.
     * @param request HttpServletRequest passed to the method
     * @return an object that implements the Request Interface
     */
    public static Request valueOfGetRequest(HttpServletRequest request){
        switch (getRequestTypeFromServletRequest(request)){
            case RESULTS:
                return new ResultsRequest(request);
            case DETAILS:
                return new DetailsRequest(request);
            case STATE:
                return new GetStateRequest(request);
            case POLLS:
                return new GetPollsRequest(request);
            default:
                return new InvalidRequest(400);
        }
    }

    /**
     * Method which parses the a Post Request path, and instantiates a Request type from it.
     * The request type is then used to return an instance of a certain Request.
     * There is default InvalidRequest returned if ever there is an unhandled request path passed.
     * @param request HttpServletRequest passed to the method
     * @return an object that implements the Request Interface
     */
    public static Request valueOfPostRequest(HttpServletRequest request){
        switch (getRequestTypeFromServletRequest(request)) {
            case VOTES:
                return new VoteRequest(request);
            case DETAILS:
                return new DetailsRequest(request);
            case POLLS:
                return new CreateRequest(request);
            case ACCESS:
                return new AccessRequest(request);
            case AUTHENTICATION:
                return new LoginRequest(request);
            case SIGNUP:
                return new SignUpRequest(request);
            case FORGOT:
                return new ForgotRequest(request);
            default:
                return new InvalidRequest(400);
        }
    }

    /**
     * Method which parses the a Put Request path, and instantiates a Request type from it.
     * The request type is then used to return an instance of a certain Request.
     * There is default InvalidRequest returned if ever there is an unhandled request path passed.
     * @param request HttpServletRequest passed to the method
     * @return an object that implements the Request Interface
     */
    public static Request valueOfPutRequest(HttpServletRequest request){
        switch (getRequestTypeFromServletRequest(request)){
            case RELEASE:
                return new ReleaseRequest(request);
            case UNRELEASE:
                return new UnreleaseRequest(request);
            case CLEAR:
                return new ClearRequest(request);
            case CLOSE:
                return new CloseRequest(request);
            case POLLS:
                return new UpdateRequest(request);
            case RUN:
                return new RunRequest(request);
            case TOKEN:
                return new TokenRequest(request);
            case CHANGE:
                return new ChangePasswordRequest(request);
            default:
                return new InvalidRequest(400);
        }
    }

    /**
     * Delete a poll.
     * @param request HttpServletRequest passed to the method from the servlet
     * @return Instance of an object which implements the Request interface.
     */
    public static Request valueOfDeleteRequest(HttpServletRequest request){
        return new DeleteRequest(request);
    }

    /**
     * This method takes a HttpServletRequest as a parameter, and returns a parsed request type
     * @param request HttpServletRequest passed
     * @return a Request_Type
     */
    public static REQUEST_TYPE getRequestTypeFromServletRequest(HttpServletRequest request){
        String basePathString = request.getServletPath();
        if(basePathString.contains("/polls/")){
            return REQUEST_TYPE.valueOf(basePathString.replace("/polls/", "").strip().trim().toUpperCase());
        }
        return REQUEST_TYPE.valueOf(basePathString.replace("/", "").strip().trim().toUpperCase());
    }
}
