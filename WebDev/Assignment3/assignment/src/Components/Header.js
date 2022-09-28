import React from 'react'

const Header = () => {
  return (
        <div class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <p class="navbar-brand" >My iPhone</p>
            <button class="navbar-toggler" data-target="#my-nav" data-toggle="collapse" aria-controls="my-nav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div id="my-nav" class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#!">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#!" tabindex="-1" >blog</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
  )
}

export default Header