# HttpServerFrame

My attempt at creating a http server framework from scratch, with socket server on Java.

It will use my [HttpBuilder](https://github.com/MathieuPPicard/HttpBuilder) to help with the creation of http message and formating it.

Http Documentation that I use to follow the protocol.
- [Mozilla](https://developer.mozilla.org/en-US/docs/Web/HTTP)
- [HttpWg](https://httpwg.org/specs/)

I will try to implement most of the specification but some details and usage case may be ignored.
## ToDo

- Default header/header-param/setting []
- Http Request 
  - Parsing []
  - Analyse(header,security) []
- Http Response
  - ...
- Server
  - Basic of a threading server [X] 
  - Handle removing client object from the list []
  - Deleting client object []
  - Resetting the id (if id 1 disconnect, the next one will have id 1) []

*Some todo put here will probably need to actually be done in my HttpBuilder.
## Installation

## Usage