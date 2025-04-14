# HttpServerFrame

My attempt at creating a http server framework from scratch, with socket server on Java.

It will use my [HttpBuilder](https://github.com/MathieuPPicard/HttpBuilder) to help with the creation of http message and formating it.

Http Documentation that I use to follow the protocol.
- [Mozilla](https://developer.mozilla.org/en-US/docs/Web/HTTP)
- [HttpWg](https://httpwg.org/specs/)

I will try to implement most of the specification but some details and usage case may be ignored.
## ToDo

- Default header
  - Default Request and response init(DefaultHeaders). [X]
    
  - Create DefaultChecker(analyse Default.Default if defaultInit() exist if yes run it).
- Http Request 
  - Parsing []
    - Use HttpBuilder to create the Http Object and easily parse through the request []
  - Analyse(header,security) []
- Http Response
  - ...
- Server
  - Basic of a threading server [X] 
  - Handle removing client object from the list [X]
  - Using UUID to identify the client connection [X]

*Some todo put here will probably need to actually be done in my HttpBuilder.
## Installation

## Usage

### Default header
If user need to set up a default Request header that will be reused to see if the incoming 
request respect a certain number of parameter in the Header.

If user need to set up a default Response header that will be reused to send in all of his
response.

#### How
1- User need to create a public void defaultInit() function in Default.Default file.

2- Used the Default.DefaultHeaders function to create and customize the defaults Headers.(See doc of DefaultHeader for usage)

3- Server will automatically compare/use the different Header.