import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    List<String> dict = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return "This is a search engine! Add words to my data!";
        } else if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    List<String> output = new ArrayList<>();
                    for (String s: dict){
                        if (s.contains(parameters[1])){
                            output.add(s);
                        }
                    }
                    if (output.isEmpty()){
                        return "We do not find such word(s) in my data!";
                    }
                    return String.format("We found %s", output.toString());
                }
        
        }else{ 
            System.out.println("Path: " + url.getPath());
            if(url.getPath().contains("/add")){
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                dict.add(parameters[1]);
                for (String s: dict){
                    return dict.toString();
                }
            }
            }
            return "404 Not Found!";
        }
        return "404 Not Found!";
    }
}


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
