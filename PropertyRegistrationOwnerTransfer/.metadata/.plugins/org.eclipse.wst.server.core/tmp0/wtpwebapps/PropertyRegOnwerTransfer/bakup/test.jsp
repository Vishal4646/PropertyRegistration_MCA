	<%
	String did = request.getParameter("email");
	
	%>
															<div class="modal fade" id="modalUpdateDoc<%=did %>" tabindex="-1" role="dialog" aria-labelledby="modalUpdatePro" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content" >
									<div class="modal-header bg-primary">
										<h6 class="modal-title">
											<i class="la la-frown-o"></i> PDFViewer
										</h6>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body text-center"  heigth="500px"width="500px">
										<embed id="pdfset" style="display:block" target="new" src="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=viewFile&fid=<%=did%>"type="application/pdf" height="400px" width=" 480px">
									
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>