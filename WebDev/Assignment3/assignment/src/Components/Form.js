import React from 'react'
import i1 from './Assets/i1.webp'
import i2 from './Assets/i2.webp'
import i3 from './Assets/i3.jpg'
import over1 from './Assets/over1.png'

const Form = () => {
  return (
    <div>
        <div class="container-fluid">
        <div id="my-carousel" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li class="active" data-target="#my-carousel" data-slide-to="0" aria-current="location"></li>
                <li data-target="#my-carousel" data-slide-to="1"></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" src={i1} alt=""/>
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Title</h5>
                        <p>Text</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src={i2} alt=""></img>
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Title</h5>
                        <p>Text</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src={i3} alt=""></img>
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Title</h5>
                        <p>Text</p>
                    </div>
                </div>
            </div>
            <a class="carousel-control-prev" href="#my-carousel" data-slide="prev" role="button">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#my-carousel" data-slide="next" role="button">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        </div>
        <br></br>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2">
                <nav class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link active" href="#!">iPhone 11</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#!" tabindex="-1" aria-disabled="true">iPhone 12</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#!" tabindex="-1" aria-disabled="true">iPhone 13</a>
                    </li>
                </nav>
            </div>
            <div class="col-sm-10">
                <h1 style={{color: 'rgb(71, 71, 239)'}}>iPhone 11</h1>
                <div class="container-fluid">
                    <div class="row">
                      <div class="col-xl-6 col-lg-6 col-md-6" style={{border:'1px solid #ddd'}}>
                        <img src={over1} alt="responsive webite" class="img-fluid"></img>
                      </div>
                      <div class="col-xl-6 col-lg-6 col-md-6" style={{border:'1px solid #ddd'}}>
                        <h1 class=".display-3">Buy iPhone 11</h1>
                        <hr></hr>
                        <br></br>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col">
                                    <h4><small>Capacity</small></h4>   
                                </div>
                                
                                <div class="col">
                                    <h4><small>128 GB</small></h4>
                                    <h4><small>256 GB</small></h4>
                                </div>
                                
                            </div>
                            <br></br>
                            <hr></hr>
                            <div class="row">
                                <div class="col">
                                    <h4><small>Size and Weight2</small></h4>   
                                </div>
                                
                                <div class="col">
                                    <h4><small>Width:</small></h4>
                                    <h4><small>75.7 mm (2.98 inches)</small></h4>
                                    <h4><small>Height</small></h4>
                                    <h4><small>150.9 mm (5.94 inches)</small></h4>
                                    <h4><small>Depth</small></h4>
                                    <h4><small>8.3 mm (0.33 inches)</small></h4>
                                </div>
                                
                            </div>
                            <br></br>
                            <hr></hr>
                            <div class="row">
                                <div class="col">
                                    <h4><small>Display</small></h4>   
                                </div>
                                
                                <div class="col">
                                    <h4 style={{lineHeight: '1.6'}}><small>
                                        Liquid Retina HD display&nbsp;
                                        6.1‑inch (diagonal) all-screen LCD Multi-Touch display with IPS technology&nbsp;
                                        1792x828‑pixel resolution at 326 ppi&nbsp;
                                        1400:1 contrast ratio (typical)&nbsp;
                                        True Tone display &nbsp;
                                        Wide colour display (P3)&nbsp;
                                        Haptic Touch&nbsp;
                                        625 nits max brightness (typical)&nbsp;
                                        Fingerprint‑resistant oleophobic coating&nbsp;
                                        Support for display of multiple languages and characters simultaneously&nbsp;
                                    </small></h4>
                                    
                                </div>
                                
                            </div>
                            
                        </div>
                        
                      </div>
                    </div>
                  </div>
                </div>
    </div>
    </div>
    </div>
  )
}

export default Form